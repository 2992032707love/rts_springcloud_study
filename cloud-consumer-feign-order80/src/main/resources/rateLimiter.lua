--获取KEY，针对哪个接口进行限流
local key = KEYS[1]
--获取注解上标注的限流次数
local limit = tonumber(ARGV[1])

local curentLimit = tonumber(redis.call('get', key) or "0")

--超过限流次数直接返回零，否则再走else分支
if curentLimit + 1 > limit
then return 0
-- 首次直接进入
else
    -- 自增长 1
    redis.call('INCRBY', key, 1)
    -- 设置过期时间
    redis.call('EXPIRE', key, ARGV[2])
    return curentLimit + 1
end