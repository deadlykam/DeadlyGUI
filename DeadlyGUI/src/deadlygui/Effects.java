/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlygui;

import com.jme3.math.Vector2f;
import deadlygui.AllEnums.Effect;

/**
 *
 * @author Kamran
 */
public class Effects extends Elements{
    protected Effect effect = Effect.NONE;
    
    public Effects(String imageLocation,
                   String UID, 
                   Vector2f position,
                   Vector2f size){
        super(imageLocation, UID, position, size);
    }
    
    public Effects(String imageLocation,
                   String hover_ImageLocation,
                   String UID, 
                   Vector2f position,
                   Vector2f size){
        super(imageLocation, hover_ImageLocation, UID, position, size);
    }
    
    public Effects(String imageLocation,
                   String imageLocation2,
                   String imageLocation3,
                   String UID, 
                   Vector2f position,
                   Vector2f size){
        super(imageLocation, imageLocation2, imageLocation3, UID, position, size);
    }
    
    protected void update_Effect(float tpf){
        
    }

    public void event_ButtonEffect(){
        if(effect == Effect.APPEAR_DISAPPEAR){
            if(visible){
                setVisible(false);
            }else{
                setVisible(true);
            }
        }
    }
    
    /**
     * @return the buttonEffect
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * @param effect the buttonEffect to set
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }

}
