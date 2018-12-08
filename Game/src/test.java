import com.PLLEngine.Control.Control;
import com.PLLEngine.Control.Control;
import com.PLLEngine.Game.Game;
import com.PLLEngine.Game.GameLoop;
import com.PLLEngine.Scene.Layer;
import com.PLLEngine.Scene.Scene;
import com.PLLEngine.Scene.layerComponents.Background;
import com.PLLEngine.Scene.layerComponents.Grid;
import com.PLLEngine.Scene.layerComponents.entity.Enemy;


import javax.swing.SwingUtilities;

public class test extends Game{
	public static test entry;
	public static GameLoop loop;
	public static void main(String[] args) {
		entry = new test() {
			// Hier kommt der Game code rein, zur �bersicht lohnt sich diese schribweise
			// Alternativ kann man das auch in der klasse selbst machen

			public void preinit() {
				// Hier nur settings reinmachen, die alle vor dem eigentlichen gam geladen
				// werden sollen zb. WindowsLook
				this.setWindowsLook();
				loop = new GameLoop(this);
			}

			public void init() {
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						createGameWindow("nam1");
						//man sollte objecte die teil des Window sind auch immer erst danach erstellen
						Layer layer1 = new Layer();
						Grid grid1 = new Grid(32,32);
						addScene("Zene1", new Scene());	//Solange die Scene nicht geladen ist passiert nix 
						getScene("Zene1").LayerCount(4);
						grid1.addMap("testmap.json");
						grid1.loadMap();
						layer1.addLayerComponents("background", new Background("Skyline.jpg"));
						layer1.addLayerComponents("olaf", new Enemy());
						layer1.addLayerComponents("Grid", grid1);
						getScene("Zene1").addLayer("test",layer1 , 0);
						addKeyListener(new Control());
						loadScene("Zene1");
						loop.start();
					}

				});
				
			}
			@Override
			public void update() {
				gwindow.repaint();
			}
		};
		entry.start();
	}

}
