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
    protected Picture image;
    protected Picture image2;
    protected Picture image3;
    protected String imageLocation = "";
    protected String imageLocation2 = "";
    protected String imageLocation3 = "";
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
        this.imageLocation2 = hover_ImageLocation;
        this.UID = UID;
        this.position = position;
        this.size = size;
    }
    
    public Elements(String imageLocation,
                    String imageLocation2,
                    String imageLocation3,
                    String UID, 
                    Vector2f position,
                    Vector2f size){
        this.imageLocation = imageLocation;
        this.imageLocation2 = imageLocation2;
        this.imageLocation3 = imageLocation3;
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
            
            if(image2 != null){
                image2.setCullHint(Spatial.CullHint.Dynamic);
            }
            
            if(image3 != null){
                image3.setCullHint(Spatial.CullHint.Dynamic);
            }
        }else{
            image.setCullHint(Spatial.CullHint.Always);
            
            if(image2 != null){
                image2.setCullHint(Spatial.CullHint.Always);
            }
            
            if(image3 != null){
                image3.setCullHint(Spatial.CullHint.Always);
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
