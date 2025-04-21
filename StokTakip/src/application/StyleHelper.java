package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class StyleHelper {
	public static void applyNavButtonStyle(Button... buttons) {
		for (Button btn : buttons) {
			btn.getStyleClass().add("nav-button");
		}
	}

	public static void applySideBarStyle(Region region) {
		region.getStyleClass().add("side-bar");
	}
}
