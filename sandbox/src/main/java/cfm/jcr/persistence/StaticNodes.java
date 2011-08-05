package cfm.jcr.persistence;

import org.apache.jackrabbit.core.id.NodeId;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

public class StaticNodes {

    private static Map<String, String> staticNodes = new HashMap<String, String>();

    static {
        staticNodes.put("cafebabe-cafe-babe-cafe-babecafebabe", "/");
        staticNodes.put("deadbeef-cafe-babe-cafe-babecafebabe", "/jcr:system");
        staticNodes.put("deadbeef-face-babe-cafe-babecafebabe", "/jcr:system/jcr:versionStorage");
        staticNodes.put("deadbeef-cafe-cafe-cafe-babecafebabe", "/jcr:system/jcr:nodeTypes");
    }

    public static boolean exists(NodeId nodeId){
        return staticNodes.containsKey(nodeId.toString());
    }

    public static String get(NodeId nodeId){
        return staticNodes.get(nodeId.toString());
    }


}
