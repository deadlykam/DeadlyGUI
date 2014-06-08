/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deadlygui.controls.ui;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.ui.Picture;
import deadlygui.AllEnums;
import deadlygui.AllEnums.Effect;
import deadlygui.Effects;
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class MetersControl extends Effects implements Control{

    private float percentage = 1.0f;
    
    private boolean init = false;
    
    public MetersControl(String background,
                         String staticBar,
                         String movableBar,
                         String UID, 
                         Vector2f position,
                         Vector2f size){
        super(background, staticBar, movableBar, UID, position, size);
    }

    public void update(float tpf) {
        if(!init){
            layerControl = spatial.getControl(LayerControl.class);
            init();
            init = true;
        }else{
            if(percentage >= 1.0f){
                image3.setCullHint(Spatial.CullHint.Always);
            }else if(percentage > 0){
               image3.setCullHint(Spatial.CullHint.Dynamic);
               image2.setCullHint(Spatial.CullHint.Dynamic);
            }else if(percentage <= 0){
                image2.setCullHint(Spatial.CullHint.Always);
            }
            
            moveBar();
        }
    }

    public void render(RenderManager rm, ViewPort vp) {
        
    }

    private void moveBar(){
        if(effect == Effect.HORIZONTAL){
            float newSize = (layerControl.getSettings().getWidth() * size.getX()) - ((layerControl.getSettings().getWidth() * size.getX()) * percentage);
            
            image3.setWidth(newSize);
        }else if(effect == Effect.HORIZONTAL){
            float newSize = (layerControl.getSettings().getHeight()* size.getY()) - ((layerControl.getSettings().getHeight()* size.getY()) * percentage);
            image3.setHeight(newSize);
        }
    }
    
    private void init(){
        image = new Picture(UID);
        image.setImage(layerControl.getApp().getAssetManager(), imageLocation, true);
        image.setWidth(layerControl.getSettings().getWidth() * size.getX());
        image.setHeight(layerControl.getSettings().getHeight() * size.getY());
        image.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image);
        
        image2 = new Picture(UID + "_HealthBar");
        image2.setImage(layerControl.getApp().getAssetManager(), imageLocation2, true);
        image2.setWidth(layerControl.getSettings().getWidth() * size.getX());
        image2.setHeight(layerControl.getSettings().getHeight() * size.getY());
        image2.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image2);
        
        image3 = new Picture(UID + "_Bar");
        image3.setImage(layerControl.getApp().getAssetManager(), imageLocation3, false);
        image3.setWidth(layerControl.getSettings().getWidth() * size.getX());
        image3.setHeight(layerControl.getSettings().getHeight() * size.getY());
        image3.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image3);
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        return this;
    }

    public void read(JmeImporter im) throws IOException {
        InputCapsule in = im.getCapsule(this);
        //this.value = in.readFloat("name", defaultValue);
    }

    public void write(JmeExporter ex) throws IOException {
        OutputCapsule out = ex.getCapsule(this);
        //out.write(this.value, "name", defaultValue);
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    /**
     * @return the percentage
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * @param percentage the percentage to set
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

}
