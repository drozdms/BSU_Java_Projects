
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.JPanel;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.awt.geom.Line2D;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class ExtrudedText extends JPanel {
    
    
    private float lightYPos=20.0f;
    private float lightZPos=2.0f;
    private Font font;
    
    private Material m;
    private JTextField textField;
    private Text3D text;
    private JButton buttonLightColor;
    private JButton buttonTextColor;
    private Color3f lightColor;
    private Color3f textColor;
    private Color3f textHighlightColor;
    private Transform3D t;
    private JSlider sliderLightPosition;
    private PointLight light;
   
  private SimpleUniverse u = null;

  public BranchGroup createSceneGraph(Canvas3D canvas) {
    Color3f eColor = new Color3f(0.0f, 0.0f, 0.0f);
    Color3f sColor = new Color3f(1.0f, 1.0f, 1.0f);
    textColor = new Color3f(Color.BLACK);
    textHighlightColor = new Color3f((Color.BLACK).brighter());
    lightColor = new Color3f(Color.YELLOW);
    Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);
    Color3f bgColor = new Color3f(0.05f, 0.05f, 0.2f);
    BranchGroup objRoot = new BranchGroup();
    TransformGroup objScale = new TransformGroup();
    Transform3D t3d = new Transform3D();
    t3d.setScale(0.3);
    objScale.setTransform(t3d);
    objRoot.addChild(objScale);
    t = new Transform3D();
    TransformGroup l2Trans = new TransformGroup(t);
    l2Trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    l2Trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    l2Trans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
    objScale.addChild(l2Trans);
    
    
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);

    // Set up the background
    Background bg = new Background(bgColor);
    bg.setApplicationBounds(bounds);
    objScale.addChild(bg);

    m = new Material(textColor, eColor, textHighlightColor, sColor, 1.0f);
    m.setCapability(Material.ALLOW_COMPONENT_WRITE);
    Appearance a = new Appearance();
    m.setLightingEnable(true);
    a.setMaterial(m);
    
    
    font=new Font("Hobbiton", Font.PLAIN, 1);
    Font3D f3d = new Font3D(font, 0.001,
        new FontExtrusion(new Line2D.Double(0,0,1.7,1)));

    text = new Text3D(f3d, "Fingolfin", new Point3f(0.0f,
        -0.5f, 0.0f));
    text.setAlignment(Text3D.ALIGN_CENTER);
    text.setCapability(Geometry.ALLOW_INTERSECT);
    text.setCapability(Text3D.ALLOW_STRING_WRITE);
    text.setCapability(Text3D.ALLOW_STRING_READ);
    Shape3D s3D2 = new Shape3D(text, a);
    l2Trans.addChild(s3D2);
    
    AmbientLight aLgt = new AmbientLight(alColor);
    aLgt.setInfluencingBounds(bounds);
    objScale.addChild(aLgt);
     light = new PointLight();
     light.setEnable(true);
    light.setColor(lightColor);
    light.setAttenuation(1.0f, 0.006f, 0.f);
    light.setPosition(new Point3f(0.0f, lightYPos, lightZPos));
     light.setCapability(Light.ALLOW_COLOR_WRITE);
    light.setCapability(PointLight.ALLOW_STATE_WRITE);
    light.setCapability(PointLight.ALLOW_POSITION_WRITE);
    light.setInfluencingBounds(bounds);
    objScale.addChild(light);
    objRoot.compile();

    return objRoot;
  }

  public ExtrudedText() 
  {
      init();
  }

  public void init() {
    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse
        .getPreferredConfiguration();
    
    Canvas3D c = new Canvas3D(config);
    add(c, BorderLayout.CENTER);
    
    u = new SimpleUniverse(c);
    BranchGroup scene = createSceneGraph(c);
    u.getViewingPlatform().setNominalViewingTransform();
    u.addBranchGraph(scene);
    JPanel panel = new JPanel(new BorderLayout());
    buttonLightColor=new JButton("Change Light Color");
    buttonLightColor.addActionListener(ae -> {
        Color color = JColorChooser.showDialog(null, "Choose Color", lightColor.get());
        if (color != null) {
            lightColor.set(color);
        }
        light.setColor(lightColor);
    });
    
    buttonTextColor=new JButton("Change Text Color");
    buttonTextColor.addActionListener(ae -> {
        Color color = JColorChooser.showDialog(null, "Choose Color", textColor.get());
        if (color != null) {
            textColor.set(color);
            textHighlightColor.set(color.brighter());
        }
        m.setAmbientColor(textColor);
        m.setDiffuseColor(textHighlightColor);
    });
    sliderLightPosition=new JSlider(-20,20,2);
    sliderLightPosition.setValue(0);
    sliderLightPosition.setMajorTickSpacing(10);
    sliderLightPosition.setPaintTicks(true);
    sliderLightPosition.setPaintLabels(true);
    sliderLightPosition.addChangeListener((ChangeEvent e) -> {
                light.setPosition((float)sliderLightPosition.getValue(),lightYPos, lightZPos);
        });
    
    
    textField=new JTextField();
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.setText(text.getString());
    textField.addActionListener(e->{
        text.setString(textField.getText());
    
    });
    
    
    panel.add(buttonLightColor, BorderLayout.WEST);
    panel.add(sliderLightPosition, BorderLayout.CENTER);
    panel.add(buttonTextColor, BorderLayout.EAST);
    add(panel, BorderLayout.NORTH);
    add(textField, BorderLayout.SOUTH);
 
  }

  public void destroy() {
    u.cleanup();
  }
}