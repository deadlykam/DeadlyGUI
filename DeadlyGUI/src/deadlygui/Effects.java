/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlygui;

import com.jme3.math.Vector2f;
import deadlygui.AllEnums.ButtonEffect;

/**
 *
 * @author Kamran
 */
public class Effects extends Elements{
    private ButtonEffect buttonEffect = ButtonEffect.NONE;
    
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
    
    protected void update_Effect(float tpf){
        
    }

    public void event_ButtonEffect(){
        if(buttonEffect == ButtonEffect.APPEAR_DISAPPEAR){
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
    public ButtonEffect getButtonEffect() {
        return buttonEffect;
    }

    /**
     * @param buttonEffect the buttonEffect to set
     */
    public void setButtonEffect(ButtonEffect buttonEffect) {
        this.buttonEffect = buttonEffect;
    }

}
