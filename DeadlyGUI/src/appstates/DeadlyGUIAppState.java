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
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import deadlygui.AllEnums;
import deadlygui.controls.ui.ButtonControl;
import deadlygui.controls.ui.LabelControl;
import deadlygui.controls.ui.LayerControl;

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

    Spatial deadlyCube;
    
    LayerControl layer;
    public ChaseCamera chaseCamera;
    
    private boolean leftClick = false;
    
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
        
        setupKeys();
        setupHUD();
        loadDeadlyCube();
        setupChaseCamera();
    }
    
    private void setupKeys(){
        inputManager.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        
        inputManager.addListener(this, "LeftClick");
    }
    
    private void loadDeadlyCube(){
        deadlyCube = assetManager.loadModel("Models/DeadlyCube/DeadlyCube1.mesh.j3o");
        
        app.getRootNode().attachChild(deadlyCube);
    }
    
    private void setupChaseCamera(){
        chaseCamera = new ChaseCamera(cam, deadlyCube, inputManager);
//        chaseCamera.setDragToRotate(false);
        chaseCamera.setDefaultDistance(10f);
//        chaseCamera.setEnabled(false);
    }
    
    private void setupHUD(){
        layer = new LayerControl(app, settings);
        guiNode.addControl(layer);
        
        LabelControl label1 = new LabelControl("Textures/DeadlyGUIImages/DeadlyLabel.png",
                                                "Label1",
                                                new Vector2f(.5f, .5f),
                                                new Vector2f(.2f, .1f));
        label1.setButtonEffect(AllEnums.ButtonEffect.APPEAR_DISAPPEAR);
//        label1.setVisible(false);
        
        
        ButtonControl button1 = new ButtonControl("Textures/DeadlyGUIImages/DeadlyButton.png",
                                                  "Textures/DeadlyGUIImages/DeadlyButton_H.png",
                                                  "Button1",
                                                  "Label1",
                                                   new Vector2f(.1f, .1f),
                                                   new Vector2f(.2f, .1f));
        
        guiNode.addControl(label1);
        guiNode.addControl(button1);
    }
    
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("LeftClick") && !isPressed){
            leftClick = true;
        }
    }
    
    @Override
    public void update(float tpf) {
        if(leftClick){
            layer.setMouseLeftClick(true);
            layer.setMousePosition(inputManager.getCursorPosition());
            leftClick = false;
        }else{
            layer.setMouseLeftClick(false);
        }
    }
}
