package cfm     

import org.apache.jackrabbit.core.{TransientRepository} 
import javax.jcr.{Repository => JCRRepo}

trait JCRAdaptor{
    // type Repository <: JCRRepo
    val repo: JCRRepo
    
}      

class TransientRepoAdaptor extends JCRAdaptor{
     val repo = new TransientRepository()
}