package com.github.maxwell.base.main;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GetMacAddress {
    public static String callCmd(String[] cmd) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param cmd     第一个命令
     * @param another 第二个命令
     * @return 第二个命令的执行结果
     */
    public static String callCmd(String[] cmd, String[] another) {
        String result = "";
        String line = "";
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor(); //已经执行完第一个命令，准备执行第二个命令
            proc = rt.exec(another);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param ip           目标ip,一般在局域网内
     * @param sourceString 命令处理的结果字符串
     * @param macSeparator mac分隔符号
     * @return mac地址，用上面的分隔符号表示
     */
    public static String filterMacAddress(final String ip, final String sourceString, final String macSeparator) {
        String result = "";
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            result = matcher.group(1);
            if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
                break; //如果有多个IP,只匹配本IP对应的Mac.
            }
        }

        return result;
    }


    /**
     * @param ip 目标ip
     * @return Mac Address
     */
    public static String getMacInWindows(final String ip) {
        String result = "";
        String[] cmd = {
                "cmd",
                "/c",
                "ping " + ip
        };
        String[] another = {
                "cmd",
                "/c",
                "arp -a"
        };

        String cmdResult = callCmd(cmd, another);
        result = filterMacAddress(ip, cmdResult, "-");

        return result;
    }

    /**
     * @param ip 目标ip
     * @return Mac Address
     */
    public static String getMacInLinux(final String ip) {
        String result = "";
        String[] cmd = {
                "/bin/sh",
                "-c",
                "ping " + ip + " -c 2 && arp -a"
        };
        String cmdResult = callCmd(cmd);
        result = filterMacAddress(ip, cmdResult, ":");

        return result;
    }

    /**
     * 获取MAC地址
     *
     * @return 返回MAC地址
     */
    public static String getMacAddress(String ip) {
        String macAddress = "";
        macAddress = getMacInWindows(ip).trim();
        if (macAddress == null || "".equals(macAddress)) {
            macAddress = getMacInLinux(ip).trim();
        }
        return macAddress;
    }

    public static String getMacAdr() {
        byte[] addr = {(byte) 192, (byte) 168, 11, 48};
        NetworkInterface ne = null;
        try {
            ne = NetworkInterface.getByInetAddress(InetAddress.getByAddress(addr));
            if (ne != null) {
                byte[] mac = ne.getHardwareAddress();
                return String.valueOf(mac);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getABC() {
        String address = "";
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe /c ipconfig /all");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                 if (line.indexOf("Physical Address") > 0) {
                    int index = line.indexOf(":");
                    index += 2;
                    address = line.substring(index);
                    log.info("address {}", address);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return address;
    }

    //做个测试
    public static void main(String[] args) {
//        System.out.println(getMacAddress("192.168.11.48"));
//        log.info("getMacAdr {}", getMacAdr());
        getABC();
    }
}
