/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deadlygui.controls.ui;

import com.jme3.asset.AssetManager;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import deadlygui.Effects;
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class LabelControl extends Effects implements Control{
    
    private boolean init = false;
    
    public LabelControl(String imageLocation, String UID, 
            Vector2f position, Vector2f size){
        super(imageLocation, UID, position, size);
    }

    public void update(float tpf) {
        if(!init){
            layerControl = spatial.getControl(LayerControl.class);
            init();
            init = true;
        }else{
            super.update_Effect(tpf);
        }
    }

    public void render(RenderManager rm, ViewPort vp) {
        
    }

    private void init(){
        image = new Picture(UID);
        image.setImage(layerControl.getApp().getAssetManager(), imageLocation, true);
        image.setWidth(layerControl.getSettings().getWidth() * size.getX());
        image.setHeight(layerControl.getSettings().getHeight() * size.getY());
        image.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image);
        
        checkVisible();
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        return this;
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    public void write(JmeExporter ex) throws IOException {
        OutputCapsule out = ex.getCapsule(this);
        //out.write(this.value, "name", defaultValue);
    }

    public void read(JmeImporter im) throws IOException {
        InputCapsule in = im.getCapsule(this);
        //this.value = in.readFloat("name", defaultValue);
    }

    
}
