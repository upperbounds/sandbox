package cfm.jcr.persistence;

import org.apache.jackrabbit.core.id.NodeId;
import org.apache.jackrabbit.core.id.PropertyId;
import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.PersistenceManager;
import org.apache.jackrabbit.core.state.*;

public class CfmPersistenceManager implements PersistenceManager{
    @Override
    public void init(PMContext pmContext) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public NodeState createNew(NodeId nodeId) {
        return null;
    }

    @Override
    public PropertyState createNew(PropertyId propertyId) {
        return null;
    }

    @Override
    public NodeState load(NodeId nodeId) throws NoSuchItemStateException, ItemStateException {
        return null;
    }

    @Override
    public PropertyState load(PropertyId propertyId) throws NoSuchItemStateException, ItemStateException {
        return null;
    }

    @Override
    public NodeReferences loadReferencesTo(NodeId nodeId) throws NoSuchItemStateException, ItemStateException {
        return null;
    }

    @Override
    public boolean exists(NodeId nodeId) throws ItemStateException {
        return false;
    }

    @Override
    public boolean exists(PropertyId propertyId) throws ItemStateException {
        return false;
    }

    @Override
    public boolean existsReferencesTo(NodeId nodeId) throws ItemStateException {
        return false;
    }

    @Override
    public void store(ChangeLog changeLog) throws ItemStateException {

    }

    @Override
    public void checkConsistency(String[] strings, boolean b, boolean b1) {

    }
}
