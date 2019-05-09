package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
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
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @RequestMapping(value = "/all")
    public @ResponseBody
    PagerBean<${entity}> getAll(PagerBean<${entity}> pager){
        if (pager.isPage())
        {
            Page<${entity}> page = PageHelper.startPage(pager.getCurPage(), pager.getLimit());
            adminService.getAllByMap(pager.getSearch());
            pager.setRowData(page.getResult());
            pager.setTotal((int) page.getTotal());
        }
        else
        {
            pager.setRowData(${table.entityPath}Service.getAllByMap(pager.getSearch()));
        }
        return pager;
    }

    /**
        * 查看
        *
        * @param id
        * @return
        */
       @RequestMapping(value = "{id}", method = RequestMethod.GET)
       public @ResponseBody
       VoAdmin get(@PathVariable("id") Long id)
       {
           ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
           Vo${entity} json${entity} = new Vo${entity}();
           CommonUtil.copyPropertiesIgnoreNull(${table.entityPath}, json${entity});
           return json${entity};
       }

        /**
       * 修改
       *
       * @param admin
       * @return
       */
      @RequestMapping(method = RequestMethod.PUT)
      public @ResponseBody Result update(Form${entity} admin)
      {
          // 修改姓名等信息
          ${entity} exist${entity} = ${table.entityPath}Service.getById(${table.entityPath}.getId());
          CommonUtil.copyPropertiesIgnoreNull(${table.entityPath}, exist${entity});
          ${table.entityPath}Service.updateById(exist${entity});
          return ResultGenerator.genSuccessResult();
      }

      /**
       * 新增
       *
       * @param admin
       * @return
       */
      @RequestMapping(method = RequestMethod.POST)
      public @ResponseBody Result save(Form${entity} ${table.entityPath})
      {
         ${entity} new${entity} = new ${entity}();
          CommonUtil.copyPropertiesIgnoreNull(${table.entityPath}, new${entity});
          ${table.entityPath}Service.save();
          return ResultGenerator.genSuccessResult();
      }


      @RequestMapping(value ="/enable", method = RequestMethod.POST)
      public @ResponseBody ResponseBean enable(Long id)
      {
        ${table.entityPath}Service.enable(id);
        return toResponseJson();
      }

	  @RequestMapping(value ="/disable", method = RequestMethod.POST)
	  public @ResponseBody ResponseBean disable(Long id)
	  {
        ${table.entityPath}Service.disable(id);
	 	return toResponseJson();
	  }
}
</#if>
