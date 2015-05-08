package top.itart.agile.common.annotation.generator;

import top.itart.agile.common.annotation.Id;

public interface Strategy {
    public Object generate(Id context);
}
