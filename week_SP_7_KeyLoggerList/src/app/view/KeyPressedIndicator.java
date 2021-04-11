
package app.view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Label;

/**
 *
 * @author Mark Drozd
 */
public class KeyPressedIndicator extends Label implements Observer 
{
    public KeyPressedIndicator() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText((String)arg);
    }

}
