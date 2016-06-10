import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Audio {

	static AudioClip shot;
	static URL clipFile = null;
	public Audio(){
		try {
			clipFile = new URL("file:"+"Audio/shot.wav");
		} catch (MalformedURLException e) {
			System.out.println("Nie wczytano jakiegoœ pliku!");
		}
	}
	public static void playShot(){
		shot=Applet.newAudioClip(clipFile);
		shot.play();
	}
}
