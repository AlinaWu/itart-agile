package top.itart.agile.common.annotation.generator;

import top.itart.agile.common.annotation.Id;
import top.itart.agile.common.util.StringUtils;

public class StrategyUUID implements Strategy{

    @Override
    public Object generate(Id context) {
        return StringUtils.UUID();
    }

}
