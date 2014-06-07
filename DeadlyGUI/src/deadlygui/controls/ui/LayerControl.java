/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deadlygui.controls.ui;

import com.jme3.app.SimpleApplication;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.system.AppSettings;
import java.io.IOException;

/**
 * This control contains SimpleApplication and AppSettings which the other controls
 * will need when added to a Node/Spatial
 * 
 * @author Kamran
 */
public class LayerControl extends AbstractControl{

    private SimpleApplication app;
    private AppSettings settings;
    private boolean mouseLeftClick = false;
    private Vector2f mousePosition = new Vector2f();
    
    public LayerControl(){}

    public LayerControl(SimpleApplication app, AppSettings settings){
        this.app = app;
        this.settings = settings;
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        //TODO: add code that controls Spatial,
        //e.g. spatial.rotate(tpf,tpf,tpf);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //Only needed for rendering-related operations,
        //not called when spatial is culled.
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        LayerControl control = new LayerControl();
        control.setSpatial(spatial);
        return control;
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
        //this.value = in.readFloat("name", defaultValue);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
        //out.write(this.value, "name", defaultValue);
    }

    /**
     * @return the app
     */
    public SimpleApplication getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(SimpleApplication app) {
        this.app = app;
    }

    /**
     * @return the settings
     */
    public AppSettings getSettings() {
        return settings;
    }

    /**
     * @param settings the settings to set
     */
    public void setSettings(AppSettings settings) {
        this.settings = settings;
    }

    /**
     * @return the mouseLeftClick
     */
    public boolean isMouseLeftClick() {
        return mouseLeftClick;
    }

    /**
     * @param mouseLeftClick the mouseLeftClick to set
     */
    public void setMouseLeftClick(boolean mouseLeftClick) {
        this.mouseLeftClick = mouseLeftClick;
    }

    /**
     * @return the mousePosition
     */
    public Vector2f getMousePosition() {
        return mousePosition;
    }

    /**
     * @param mousePosition the mousePosition to set
     */
    public void setMousePosition(Vector2f mousePosition) {
        this.mousePosition = mousePosition;
    }


}
