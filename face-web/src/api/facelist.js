import request from '@/utils/request'

export function getfacelist() {
    return request({
        url: 'admin/faceList',
        method: 'get'
    })
}
export function getface(username) {
    return request({
        url: 'admin/faceinfo/'+username,
        method: 'get'
    })
}
export function delface(username) {
    return request({
        url: 'admin/deluser/' + username,
        method: 'get'
    })
}