import request from '@/utils/request'

export function getAdminList(token) {
    return request({
        url: 'admin/findAll/'+token,
        method: 'get'
    })
}
export function changeRole(adminId) {
    return request({
        url: 'admin/changeRole/',
        method: 'post',
        params:{
            "adminId":adminId
        }
    })
}
