import java.io.File;
import java.io.RandomAccessFile;

public class BuildClass {

	public final static String ROOT_SRC = "E:\\workspace\\erlon\\thinking in java 4.0\\src\\";
	public static RandomAccessFile randomAccessFile;

	public static void main(String[] args) throws Exception {
		File root = new File(ROOT_SRC);
		secrch(root);
	}

	public static void secrch(File file) throws Exception {

		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				secrch(listFiles[i].getAbsoluteFile());
			}
		}

		if (file.isFile()) {
			if (file.getName().endsWith(".java")) {

				if (file.getName().equals(file.getAbsolutePath().replace(ROOT_SRC, ""))) {
					System.err.println(file.getName());
					return;
				}

				String pack = "package " + file.getAbsolutePath().replace(ROOT_SRC, "").replace("\\", ".")
						.replace("." + file.getName(), "") + ";";
				String packn = pack + "\n";

				randomAccessFile = new RandomAccessFile(file, "rw");
				long length = randomAccessFile.length();
				byte[] all = new byte[(int) length];
				randomAccessFile.readFully(all);
				String allStr = new String(all);
				if (allStr.indexOf(pack) < 0) {
					System.out.println(file.getAbsolutePath());
					randomAccessFile.seek(0);
					randomAccessFile.write(packn.getBytes());
					randomAccessFile.write(all);
				}
				randomAccessFile.close();
			}
		}
	}

}
