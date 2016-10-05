/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oobd.core.port;

import org.oobd.core.OOBDConstants;
import org.oobd.core.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.oobd.core.bus.OobdBus;
import org.oobd.core.port.OOBDPort;
import org.oobd.core.port.PortInfo;
import org.oobd.core.support.Onion;
//import gnu.io.*; // for rxtxSerial library
import jssc.*;
import java.io.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author jayakumar
 */
public class ComPort_Win implements OOBDPort, SerialPortEventListener {

    String[] portList;
    String portname;
    SerialPort serialPort;
    OobdBus msgReceiver;
    String defaultPort = "";

    public boolean connect(Onion options, OobdBus receiveListener) {
        if (serialPort != null) {
            close();
        }
        msgReceiver = receiveListener;

        defaultPort = Settings.getString(OOBDConstants.PropName_ConnectTypeBT + "_" + OOBDConstants.PropName_SerialPort, "");
        serialPort = new SerialPort(defaultPort);
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(115200, 8, 1, 0);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
        } catch (SerialPortException ex) {
            System.out.println(ex);
            Logger.getLogger(ComPort_Win.class.getName()).log(Level.WARNING, "serial port " + defaultPort + " not found.");
            return false;
        }
        attachShutDownHook();
        return true;
    }

    public void close() {
        System.out.println("CLOSE PORT!! " + serialPort);
        if (serialPort != null) {
            try {
                serialPort.removeEventListener();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
            try {
                serialPort.closePort();
                serialPort = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.err.println("Inside Add Shutdown Hook");
                close();
                System.err.println("Serial line closed");
            }
        });
        System.err.println("Shut Down Hook Attached.");

    }

    public void serialEvent(SerialPortEvent spe) {
        if (spe.isRXCHAR() && spe.getEventValue() > 0) {
            try {
                byte buffer[] = serialPort.readBytes(spe.getEventValue());
                msgReceiver.receiveString(new String(buffer));
            } catch (SerialPortException ex) {
                System.out.println("Error in receiving string from COM-port: " + ex);
            }
        }
    }

    public synchronized void write(String s) {
        try {
            Logger.getLogger(ComPort_Win.class.getName()).log(Level.INFO,
                    "Serial output:" + s);
            serialPort.writeBytes(s.getBytes());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String connectInfo() {
        if (serialPort == null) {
            return "BT: Not connected";
        } else {
            return "BT connected to " + defaultPort;
        }
    }

    public static String getUrlFormat() {
        return "serial://{connectid}";
    }

    public static PortInfo[] getPorts() {
        PortInfo[] DeviceSet;
        String osName = System.getProperty("os.name", "").toLowerCase();
        Logger.getLogger(ComPort_Win.class.getName()).log(Level.CONFIG, "OS detected: {0}", osName);

        if (osName.startsWith("windows")) {
            String[] WindowsPortList = SerialPortList.getPortNames();
            DeviceSet = new PortInfo[WindowsPortList.length];
            // Process the list.
            for (int i = 0; i < WindowsPortList.length; i++) {
                DeviceSet[i] = new PortInfo(WindowsPortList[i], WindowsPortList[i]);
            }
        } else {
            DeviceSet = new PortInfo[1];
            DeviceSet[0] = new PortInfo("", "No Devices visible");
        }
        return DeviceSet;
    }

    public int adjustTimeOut(int originalTimeout) {
        return originalTimeout;
    }
}
