package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
     List<${entity}> getAllByMap(Map<String,Object> params);
     Long countByMap(Map<String,Object> params);
}
