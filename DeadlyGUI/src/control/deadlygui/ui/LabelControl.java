/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control.deadlygui.ui;

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
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class LabelControl extends AbstractControl{

    AssetManager assetManager;
    private Node guiNode;
    AppSettings settings;
    private Picture image = new Picture();
    String imageLocation = "";
    String UID = "";
    Vector2f position = new Vector2f();
    Vector2f size = new Vector2f();
    
    public LabelControl(){}
    
    public LabelControl(Node guiNode, AssetManager assetManager, AppSettings settings, String imageLocation, String UID, 
            Vector2f position, Vector2f size){
        this.guiNode = guiNode;
        this.assetManager = assetManager;
        this.settings = settings;
        this.imageLocation = imageLocation;
        this.UID = UID;
        this.position = position;
        this.size = size;
        
        init();
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

    private void init(){
        image = new Picture(UID);
        image.setImage(assetManager, imageLocation, true);
        image.setWidth(settings.getWidth() * size.getX());
        image.setHeight(settings.getHeight() * size.getY());
        image.setPosition(settings.getWidth() * position.x, settings.getHeight() * position.y);
//        System.out.println("Settings Width: " + settings.getH);
        guiNode.attachChild(getImage());
        System.out.println("Image Attached!");
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial) {
        LabelControl control = new LabelControl();
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
     * @return the image
     */
    public Picture getImage() {
        return image;
    }


}
