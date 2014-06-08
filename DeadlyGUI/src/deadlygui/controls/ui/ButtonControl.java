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
    
    public ButtonControl(String defaultImage,
                         String UID, 
                         Vector2f position,
                         Vector2f size){
        super(defaultImage, UID, position, size);
    }
    
    public ButtonControl(String defaultImage,
                         String UID, 
                         Vector2f position,
                         Vector2f size,
                         String target){
        super(defaultImage, UID, position, size);
        
        this.target = target;
    }
    
    public ButtonControl(String defaultImage,
                         String clickImage,
                         String UID, 
                         Vector2f position,
                         Vector2f size){
        super(defaultImage, clickImage, UID, position, size);
    }

    public ButtonControl(String defaultImage,
                         String clickImage,
                         String UID,
                         String target,
                         Vector2f position,
                         Vector2f size){
        super(defaultImage, clickImage, UID, position, size);
        
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
                    if(image2 != null){
                        image2.setCullHint(Spatial.CullHint.Dynamic);
                        image.setCullHint(Spatial.CullHint.Always);
                    }
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
            }else if(image2 != null){
                image2.setCullHint(Spatial.CullHint.Always);
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
        
        if(!imageLocation2.equals("")){
            image2 = new Picture(UID + "_hover");
            image2.setImage(layerControl.getApp().getAssetManager(), imageLocation2, true);
            image2.setWidth(layerControl.getSettings().getWidth() * size.getX());
            image2.setHeight(layerControl.getSettings().getHeight() * size.getY());
            image2.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
            image2.setCullHint(Spatial.CullHint.Always);
            layerControl.getApp().getGuiNode().attachChild(image2);
        }
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
