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
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.ui.Picture;
import deadlygui.Effects;
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class ScrollControl extends Effects implements Control{

    private float percentage = 0.0f;
    private float offset = 0.05f;
    private float P1x = 0, P2x = 0;
    
    private Vector2f [] sizes;
    
    private boolean init = false;
    
    public ScrollControl(String background,
                         String leftNavigator,
                         String rightNavigator,
                         String scrollBar,
                         String UID, 
                         Vector2f position,
                         Vector2f size){
        super(background, leftNavigator, rightNavigator, scrollBar, UID, position, size);
    }
    
    public ScrollControl(String background,
                         String leftNavigator,
                         String rightNavigator,
                         String scrollBar,
                         String UID, 
                         Vector2f position,
                         Vector2f [] sizes){
        super(background, leftNavigator, rightNavigator, scrollBar, UID, position);
        
        this.sizes = sizes;
    }
    
    public void update(float tpf) {
        if(!init){
            layerControl = spatial.getControl(LayerControl.class);
            init();
            init = true;
        }else{
            if(layerControl.isMouseLeftClickHold()){
                if(layerControl.getMousePosition().x >= (layerControl.getSettings().getWidth() * position.x) && 
                   layerControl.getMousePosition().x <= ((layerControl.getSettings().getWidth() * position.x) + (layerControl.getSettings().getWidth() * sizes[1].x)) && 
                   layerControl.getMousePosition().y >= (layerControl.getSettings().getHeight()* position.y) && 
                   layerControl.getMousePosition().y <= ((layerControl.getSettings().getHeight()* position.y) + (layerControl.getSettings().getHeight()* sizes[1].y))){
                    //Left Navigator
                    moveScrollBar(-1, tpf);
                }else if(layerControl.getMousePosition().x >= (layerControl.getSettings().getWidth() * P2x) && 
                   layerControl.getMousePosition().x <= ((layerControl.getSettings().getWidth() * P2x) + (layerControl.getSettings().getWidth() * sizes[2].x)) && 
                   layerControl.getMousePosition().y >= (layerControl.getSettings().getHeight()* position.y) && 
                   layerControl.getMousePosition().y <= ((layerControl.getSettings().getHeight()* position.y) + (layerControl.getSettings().getHeight()* sizes[2].y))){
                    //Right Navigator
                    moveScrollBar(1, tpf);
                }
            }
            
            if(layerControl.isMouseLeftClick()){
                if(layerControl.getMousePosition().x >= (layerControl.getSettings().getWidth() * P1x) && 
                   layerControl.getMousePosition().x <= ((layerControl.getSettings().getWidth() * P1x) + (layerControl.getSettings().getWidth() * sizes[0].x)) && 
                   layerControl.getMousePosition().y >= (layerControl.getSettings().getHeight()* position.y) && 
                   layerControl.getMousePosition().y <= ((layerControl.getSettings().getHeight()* position.y) + (layerControl.getSettings().getHeight()* sizes[0].y))){
                    instantMove();
                }
            }
        }
    }

    public void render(RenderManager rm, ViewPort vp) {
        
    }

    private void moveScrollBar(int dir, float tpf){
        percentage += (offset * dir * tpf);
        
        if(percentage >= 1){
//            percentage = 1.0f - (sizes[3].x * 2);
            percentage = 1.0f;
        }else if(percentage < 0.0f){
            percentage = 0.0f;
        }
        
        if(percentage >= (1.0f - (sizes[3].x * 2))){
            image4.setPosition(layerControl.getSettings().getWidth() * ((sizes[0].x * (1 - (sizes[3].x * 2))) + P1x), layerControl.getSettings().getHeight() * position.y);
        }else{   
            image4.setPosition(layerControl.getSettings().getWidth() * ((sizes[0].x * percentage) + P1x), layerControl.getSettings().getHeight() * position.y);
        }
        System.out.println("Percentage: " + percentage);
    }
    
    private void instantMove(){
        float lengthFromMouse = layerControl.getMousePosition().x - layerControl.getSettings().getWidth() * P1x;
        float length = (layerControl.getSettings().getWidth() * P2x) - layerControl.getSettings().getWidth() * P1x;
        percentage = lengthFromMouse / length;
        System.out.println("Percentage: " + percentage);
        if(percentage >= (1.0f - (sizes[3].x * 2))){
            image4.setPosition(layerControl.getSettings().getWidth() * ((sizes[0].x * (1 - (sizes[3].x * 2))) + P1x), layerControl.getSettings().getHeight() * position.y);
        }else{   
            image4.setPosition(layerControl.getSettings().getWidth() * ((sizes[0].x * percentage) + P1x), layerControl.getSettings().getHeight() * position.y);
        }
    }
    
    private void init(){
        P1x = position.x + sizes[1].x;
        P2x = P1x + sizes[0].x;
        
        image = new Picture(UID);
        image.setImage(layerControl.getApp().getAssetManager(), imageLocation, true);
        image.setWidth(layerControl.getSettings().getWidth() * sizes[0].getX());
        image.setHeight(layerControl.getSettings().getHeight() * sizes[0].getY());
        image.setPosition(layerControl.getSettings().getWidth() * P1x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image);
        
        image2 = new Picture(UID + "_Left");
        image2.setImage(layerControl.getApp().getAssetManager(), imageLocation2, true);
        image2.setWidth(layerControl.getSettings().getWidth() * sizes[1].getX());
        image2.setHeight(layerControl.getSettings().getHeight() * sizes[1].getY());
        image2.setPosition(layerControl.getSettings().getWidth() * position.x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image2);
        
        image3 = new Picture(UID + "_Right");
        image3.setImage(layerControl.getApp().getAssetManager(), imageLocation3, true);
        image3.setWidth(layerControl.getSettings().getWidth() * sizes[2].getX());
        image3.setHeight(layerControl.getSettings().getHeight() * sizes[2].getY());
        image3.setPosition(layerControl.getSettings().getWidth() * P2x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image3);
        
        image4 = new Picture(UID + "_Bar");
        image4.setImage(layerControl.getApp().getAssetManager(), imageLocation4, true);
        image4.setWidth(layerControl.getSettings().getWidth() * sizes[3].getX());
        image4.setHeight(layerControl.getSettings().getHeight() * sizes[3].getY());
        image4.setPosition(layerControl.getSettings().getWidth() * P1x, layerControl.getSettings().getHeight() * position.y);
        layerControl.getApp().getGuiNode().attachChild(image4);
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        return this;
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        InputCapsule in = im.getCapsule(this);
        //this.value = in.readFloat("name", defaultValue);
    }

    @Override
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

    /**
     * @return the offset
     */
    public float getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(float offset) {
        this.offset = offset;
    }
}
