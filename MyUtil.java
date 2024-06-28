import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class MyUtil {
    public static List<String> readZipFile(String file) {
        List<String> result = new ArrayList<>();
        try {
            ZipFile zf = new ZipFile(file);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    continue;
                }
                String name = ze.getName();
                if (name.contains("/")) {
                    name = name.substring(name.lastIndexOf("/") + 1);
                }
                result.add(name);
                System.err.println("file - " + name + " : " + ze
                        .getSize() + " bytes");
            }
            zin.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
            result.add("ERROR:" + e.getMessage());
        }
        return result;
    }


    public static void readAPK(String apkUrl) {
        try {
            ZipFile zipFile = new ZipFile(apkUrl);
            Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = enumeration.nextElement();
                if (zipEntry.isDirectory()) ;
            }
            zipFile.close();
        } catch (Exception ignored) {
        }
    }
}






