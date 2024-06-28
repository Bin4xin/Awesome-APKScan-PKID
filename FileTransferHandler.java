import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.TransferHandler;


class FileTransferHandler extends TransferHandler {
    private ApkScanWin apkScanWin;
    public FileTransferHandler(ApkScanWin apkScanWin) {
        this.apkScanWin = apkScanWin;
    }
    public boolean importData(JComponent comp, Transferable t) {
        try {
            List<File> list = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
            Iterator<File> iterator = list.iterator();
            if (iterator.hasNext()) {
                File f = iterator.next();
                this.apkScanWin.getTextField1().setText(f.getAbsolutePath());
                List<String> result = MyUtil.readZipFile(f.getAbsolutePath());

                if (result.size() != 1) {
                    boolean flag = true;

                    for (String fileName : result) {
                        if (this.apkScanWin.getMarkNameMap().containsKey(fileName)) {
                            this.apkScanWin.getTextField2().setText("《" + (String) this.apkScanWin.getMarkNameMap().get(fileName) + "》加固");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        this.apkScanWin.getTextField2().setText("此apk未采用加固或为未知加固厂商！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean canImport(TransferHandler.TransferSupport support) {
        return true;
    }
}






