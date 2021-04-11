
import java.util.Set;

/**
 *
 * @author Mark Drozd
 */
public class Notifier 
{
    
    Notifier(Set<? extends Notifiable> notifiables)
    {
        this.notifiables=notifiables;
    }
    
    public void doNotifyAll(String message)
    {
        notifiables.forEach((notifiable)->
        {
            notifiable.notify(message);
        });
    }
    
    
    private Set<? extends Notifiable> notifiables;

}
