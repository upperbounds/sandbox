package cfm.jcr.persistence;

import org.apache.jackrabbit.core.id.NodeId;
import org.apache.jackrabbit.core.id.PropertyId;
import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.PersistenceManager;
import org.apache.jackrabbit.core.state.*;

import org.bouncycastle.util.test.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.Node;
import java.awt.*;
import java.io.File;


public class CfmPersistenceManager implements PersistenceManager {

    private static final Logger log = LoggerFactory.getLogger(CfmPersistenceManager.class);

    private String url;

    private PMContext pmContext;

    private NodeId rootNodeId;

    @Override
    public void init(PMContext pmContext) throws Exception {
        log.debug("init called:");
        log.debug("DataStore: {}", pmContext.getDataStore());
        log.debug("FileSystem: {}", pmContext.getFileSystem());
        log.debug("HomeDir: {}", pmContext.getHomeDir());
        log.debug("NamespaceRegistry: {}", pmContext.getNamespaceRegistry());
        //log.debug("NodeTypeRegistry: {}", pmContext.getNodeTypeRegistry());
        log.debug("RootNodeId: {}", pmContext.getRootNodeId());
        this.pmContext = pmContext;

        rootNodeId = pmContext.getRootNodeId();

        File db = new File("test.db");


        // do something here
    }

    @Override
    public void close() throws Exception {
        log.debug("close called");

    }

    @Override
    public NodeState createNew(NodeId nodeId) {
        log.debug("create node called for {}", nodeId);
        return null;
    }

    @Override
    public PropertyState createNew(PropertyId propertyId) {
        log.debug("create property called for {}", propertyId);
        return null;
    }

    @Override
    public NodeState load(NodeId nodeId) throws NoSuchItemStateException, ItemStateException {
        log.debug("load called for node {}", nodeId);
        return null;
    }

    @Override
    public PropertyState load(PropertyId propertyId) throws NoSuchItemStateException, ItemStateException {
        log.debug("load called for property {}", propertyId);
        return null;
    }

    @Override
    public NodeReferences loadReferencesTo(NodeId nodeId) throws NoSuchItemStateException, ItemStateException {
        log.debug("load references called for node {}", nodeId);
        return null;
    }

    @Override
    public boolean exists(NodeId nodeId) throws ItemStateException {
        log.debug("exists called for node {}", nodeId);
        if(nodeId.equals(rootNodeId)){
            return true;
        }

        return false;
    }

    @Override
    public boolean exists(PropertyId propertyId) throws ItemStateException {
        log.debug("exists called for property {}", propertyId);
        return false;
    }

    @Override
    public boolean existsReferencesTo(NodeId nodeId) throws ItemStateException {
        log.debug("existsReferencesTo called for node {}", nodeId);

        return false;
    }

    @Override
    public void store(ChangeLog changeLog) throws ItemStateException {
        log.debug("store called for changelog {}", changeLog);

    }

    @Override
    public void checkConsistency(String[] strings, boolean b, boolean b1) {
        Object[] args = {strings, b,b1};
        log.debug("checkConsistency called for changelog {}, {} {}", args);
    }


}
