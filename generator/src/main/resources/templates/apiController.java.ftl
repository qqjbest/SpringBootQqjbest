package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>
 * ${table.comment!} 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/api/${table.entityPath}")
public class ${table.controllerName} {



}
