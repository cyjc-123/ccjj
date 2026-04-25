package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 切入点：controller包下所有方法
     */
    @Pointcut("@annotation(org.example.anno.Log)")
    public void pt(){}

    /**
     * 环绕通知
     */
    @Around("pt()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. 记录开始时间
        long begin = System.currentTimeMillis();

        // 2. 执行原方法
        Object result = joinPoint.proceed();

        // 3. 记录结束时间
        long end = System.currentTimeMillis();

        // 4. 构建日志对象
        OperateLog log = new OperateLog();

        // 操作人（这里先写死，实际应从登录用户获取）
        log.setOperateEmpId(getCurrentUserId());

        // 操作时间
        log.setOperateTime(LocalDateTime.now());

        // 类名
        String className = joinPoint.getTarget().getClass().getName();
        log.setClassName(className);

        // 方法名
        String methodName = joinPoint.getSignature().getName();
        log.setMethodName(methodName);

        // 方法参数
        Object[] args = joinPoint.getArgs();
        log.setMethodParams(Arrays.toString(args));

        // 返回值
        log.setReturnValue(result != null ? result.toString() : null);

        // 执行耗时
        log.setCostTime(end - begin);

        // 5. 保存日志
        operateLogMapper.insert(log);

        return result;
    }

    /**
     * 获取当前登录用户ID（示例）
     */
    private Integer getCurrentUserId() {
        // 实际开发中从 ThreadLocal / Token / Session 获取

        return CurrentHolder.getCurrentId();
    }
}