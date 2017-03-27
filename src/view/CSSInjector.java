package view;

import java.net.URL;

import javafx.scene.Scene;

public class CSSInjector {
	public static void setStandard(Scene scene, Object object){
		URL url = object.getClass().getResource("layoutStyle.css");
		String css = url.toExternalForm();
		scene.getStylesheets().add(css);
	}
}
