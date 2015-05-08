package top.itart.agile.common.annotation.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import top.itart.agile.common.annotation.Id;
import top.itart.agile.common.util.BeanUtils;
import top.itart.agile.common.util.StringUtils;

public class StrategyFactory {

    private static Map<Class<? extends Strategy>, Strategy> generatorInstances = new HashMap<Class<? extends Strategy>, Strategy>();

    public static void setId(Object model) {

        Field[] fields = BeanUtils.getFields(model);
        try {
            for (Field field : fields) {
                Id context = field.getAnnotation(Id.class);

                if (context != null) {
                    Method method;
                    method = model.getClass().getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());
                    method.invoke(model, generate(context));
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object generate(Id context) {
        
        Strategy strategy = generatorInstances.get(context.strategy());
        if (null == strategy) {
            try {
                strategy = context.strategy().newInstance();
                generatorInstances.put(context.strategy(), strategy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return strategy.generate(context);
    }

}
