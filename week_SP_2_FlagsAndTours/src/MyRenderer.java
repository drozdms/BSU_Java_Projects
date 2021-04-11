
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Mark Drozd
 */
 public class MyRenderer extends DefaultTreeCellRenderer 
 {
        Icon universityIcon;
        Icon courseIcon;
        Icon groupIcon;
        Icon personIcon;
 
        public MyRenderer(Icon icon1, Icon icon2, Icon icon3, Icon icon4) 
        {
            universityIcon = icon1;
            courseIcon=icon2;
            groupIcon=icon3;
            personIcon=icon4;
        }
 
        @Override
        public Component getTreeCellRendererComponent(
                            JTree tree,
                            Object value,
                            boolean sel,
                            boolean expanded,
                            boolean leaf,
                            int row,
                            boolean hasFocus) 
        {
 
            super.getTreeCellRendererComponent(
                            tree, value, sel,
                            expanded, leaf, row,
                            hasFocus);
            switch (((TreeNode)value).getLevel()) {
                case 0:
                    setIcon(universityIcon);
                    break;
                case 1:
                    setIcon(courseIcon);
                    break;
                case 2:
                    setIcon(groupIcon);
                    break;
                default:
                    setIcon(personIcon);
                    break;
            }
 
            return this;
        }
 
       
    }
