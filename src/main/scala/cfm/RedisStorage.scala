package cfm

object RedisStorage{
    
    import com.redis._
    
    val redis: RedisClient = new RedisClient()
    
    
    def connect = redis.connect
}