/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlygui;

import com.jme3.math.Vector2f;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;
import deadlygui.controls.ui.LayerControl;

/**
 *
 * @author Kamran
 */
public class Elements {
    protected Spatial spatial;
    protected LayerControl layerControl = new LayerControl();
    protected Picture image = new Picture();
    protected Picture image_Hover;
    protected String hover_ImageLocation = "";
    protected String imageLocation = "";
    protected String UID = "";
    protected Vector2f position = new Vector2f();
    protected Vector2f size = new Vector2f();
    protected boolean visible = true;
    
    public Elements(String imageLocation,
                    String UID, 
                    Vector2f position,
                    Vector2f size){
        this.imageLocation = imageLocation;
        this.UID = UID;
        this.position = position;
        this.size = size;
    }

    public Elements(String imageLocation,
                    String hover_ImageLocation,
                    String UID, 
                    Vector2f position,
                    Vector2f size){
        this.imageLocation = imageLocation;
        this.hover_ImageLocation = hover_ImageLocation;
        this.UID = UID;
        this.position = position;
        this.size = size;
    }
    
    /**
     * Checks the visible boolean and sets the cullhint
     */
    protected void checkVisible(){
        if(visible){
            image.setCullHint(Spatial.CullHint.Dynamic);
            
            if(image_Hover != null){
                image_Hover.setCullHint(Spatial.CullHint.Dynamic);
            }
        }else{
            image.setCullHint(Spatial.CullHint.Always);
            
            if(image_Hover != null){
                image_Hover.setCullHint(Spatial.CullHint.Always);
            }
        }
    }
    
    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
        checkVisible();
    }
}
