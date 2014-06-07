/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appstates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector2f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import control.deadlygui.ui.LabelControl;

/**
 *
 * @author Kamran
 */
public class DeadlyGUIAppState extends AbstractAppState implements ActionListener{
    private SimpleApplication app;
    private AssetManager      assetManager;
    private Node              guiNode;
    private AppStateManager   stateManager;
    private InputManager      inputManager;
    private Camera            cam;
    private AppSettings       settings;

    public DeadlyGUIAppState(AppSettings settings){
        this.settings = settings;
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.guiNode      = this.app.getGuiNode();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.stateManager = this.app.getStateManager();
        this.cam          = this.app.getCamera();
        
        setupHUD();
    }
    
    private void setupHUD(){
        LabelControl label1 = new LabelControl(guiNode, assetManager, settings,
                "Textures/DeadlyGUIImages/DeadlyLabel.png", "Label1",
                new Vector2f(.5f, .5f), new Vector2f(.5f, .4f));
        
        guiNode.addControl(label1);
    }
    
    public void onAction(String name, boolean isPressed, float tpf) {
        
    }
}
