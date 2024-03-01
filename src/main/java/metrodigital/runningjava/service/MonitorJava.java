package metrodigital.runningjava.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Date;

@Service
public class MonitorJava {
    @Scheduled(fixedDelay = 60000)
    public void monitorJavaProcessesAndVersion() {
        try {
            String command = "wmic process where \"name='java.exe' or name='javaw.exe'\" get ExecutablePath";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0 && !line.trim().equalsIgnoreCase("ExecutablePath")) {
                    executeJavaVersion(line.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeJavaVersion(String javaPath) {
        try {

            String command = javaPath + " -version";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String versionInfo = errorReader.readLine();
            String hostname = InetAddress.getLocalHost().getHostName();
            if (versionInfo != null && !versionInfo.isEmpty()) {
                log(hostname,javaPath,versionInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void log(String hostname,String javaPath, String message) {
        System.out.println(new Date() +" hostname:" +hostname + " cale:"+javaPath+ " versiune:"+message);

    }
}
