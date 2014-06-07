package mygame;

import appstates.DeadlyGUIAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    DeadlyGUIAppState deadlyGUIAppState;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setupAppStateClasses();
        loadDeadlyGUI();
    }

    private void setupAppStateClasses(){
        deadlyGUIAppState = new DeadlyGUIAppState(settings);
    }
    
    private void loadDeadlyGUI(){
        stateManager.attach(deadlyGUIAppState);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
