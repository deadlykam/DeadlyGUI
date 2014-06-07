/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control.controls;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 *
 * @author Kamran
 */
public class RotationControl extends AbstractControl{

    private Vector3f rot_Dir = new Vector3f();
    
    public RotationControl(){}

    @Override
    protected void controlUpdate(float tpf) {
        spatial.rotate(rot_Dir.x * tpf, rot_Dir.y * tpf, rot_Dir.z * tpf);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //Only needed for rendering-related operations,
        //not called when spatial is culled.
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        RotationControl control = new RotationControl();
        control.setRot_Dir(rot_Dir);
        control.setSpatial(spatial);
        return control;
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
        rot_Dir = (Vector3f) in.readSavable("rot_Dir", new Vector3f());
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
        out.write(rot_Dir, "rot_Dir", new Vector3f());
    }

    /**
     * @return the rot_Dir
     */
    public Vector3f getRot_Dir() {
        return rot_Dir;
    }

    /**
     * @param rot_Dir the rot_Dir to set
     */
    public void setRot_Dir(Vector3f rot_Dir) {
        this.rot_Dir = rot_Dir;
    }
}
