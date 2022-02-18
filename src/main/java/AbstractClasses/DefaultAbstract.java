package AbstractClasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultAbstract {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
}
