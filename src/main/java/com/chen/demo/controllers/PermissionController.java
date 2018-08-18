package com.chen.demo.controllers;

import com.chen.demo.commons.VoUtil;
import com.chen.demo.exceptions.BusinessException;
import com.chen.demo.models.Permission;
import com.chen.demo.services.PermissionService;
import com.chen.demo.vos.PermissionVo;
import com.chen.demo.vos.ResponseData;
import com.chen.demo.vos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ResponseData add(@RequestBody PermissionVo vo) throws Exception{
        Permission permission = (Permission) VoUtil.voToModel(vo, Permission.class);
        if(permissionService.findByCode(permission.getCode()) != null){
            throw new BusinessException(ResponseStatus.PERMISSION_CODE_EXIST);
        }
        if(permissionService.findByApi(permission.getApi()) != null){
            throw new BusinessException(ResponseStatus.PERMISSION_API_EXIST);
        }
        permissionService.add(permission);
        return ResponseData.success(new PermissionVo(permission));
    }

    @PutMapping("/{id}")
    public ResponseData edit(@RequestBody PermissionVo vo, @PathVariable Long id) throws Exception{
        if(permissionService.findByID(id) == null){
            throw new BusinessException(ResponseStatus.PERMISSION_NOT_EXIST);
        }
        Permission permission = (Permission) VoUtil.voToModel(vo, Permission.class);
        permission.setId(id);
        permissionService.update(permission);
        return ResponseData.success(new PermissionVo(permissionService.findByID(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) throws BusinessException{
        if(permissionService.findByID(id) == null){
            throw new BusinessException(ResponseStatus.PERMISSION_NOT_EXIST);
        }
        permissionService.delete(id);
        return ResponseData.success();
    }

    @GetMapping("/{id}")
    public ResponseData findByID(@PathVariable Long id) throws BusinessException{
        if(permissionService.findByID(id) == null){
            throw new BusinessException(ResponseStatus.PERMISSION_NOT_EXIST);
        }
        return ResponseData.success(new PermissionVo(permissionService.findByID(id)));
    }
}
