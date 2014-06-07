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
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.ui.Picture;
import deadlygui.Effects;
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class ButtonControl extends Effects implements Control{
    
    private boolean selected = false;
    private String target = "";
    
    private boolean init = false;
    
    
    public ButtonControl(String imageLocation,
                         String hover_ImageLocation,
                         String UID, 
                         Vector2f position,
                         Vector2f size){
        super(imageLocation, hover_ImageLocation, UID, position, size);
    }

    public ButtonControl(String imageLocation,
                         String hover_ImageLocation,
                         String UID,
                         String target,
                         Vector2f position,
                         Vector2f size){
        super(imageLocation, hover_ImageLocation, UID, position, size);
        
        this.target = target;
    }
    
    public void update(float tpf) {
        if(!init){
            layerControl = spatial.getControl(LayerControl.class);
            init();
            init = true;
        }else{
            if(layerControl.isMouseLeftClick()){
                if(layerControl.getMousePosition().x >= (layerControl.getSettings().getWidth() * position.x) && 
                   layerControl.getMousePosition().x <= ((layerControl.getSettings().getWidth() * position.x) + (layerControl.getSettings().getWidth() * size.x)) && 
                   layerControl.getMousePosition().y >= (layerControl.getSettings().getHeight()* position.y) && 
                   layerControl.getMousePosition().y <= ((layerControl.getSettings().getHeight()* position.y) + (layerControl.getSettings().getHeight()* size.y))){
                    image_Hover.setCullHint(Spatial.CullHint.Dynamic);
                    image.setCullHint(Spatial.CullHint.Always);
                    
                    if(!target.equals("")){
                        for(int i = 0; i < spatial.getNumControls(); i++){
                            if(spatial.getControl(i) instanceof LabelControl){
                                LabelControl lc = (LabelControl) spatial.getControl(i);
                                if(lc != null){
                                    ((Effects) lc).event_ButtonEffect();
                                }
                            }
                        }
                    }
                }
//                System.out.println("Button Click");
            }else{
                image_Hover.setCullHint(Spatial.CullHint.Always);
                image.setCullHint(Spatial.CullHint.Dynamic);
            }
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
        
        image_Hover = new Picture(UID + "_hover");
        image_Hover.setImage(layerControl.getApp().getAssetManager(), hover_ImageLocation, true);
        image_Hover.setWidth(layerControl.getSettings().getWidth() * size.getX());
        image_Hover.setHeight(layerControl.getSettings().getHeight() * size.getY());
        image_Hover.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        image_Hover.setCullHint(Spatial.CullHint.Always);
        layerControl.getApp().getGuiNode().attachChild(image_Hover);
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

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    


}
