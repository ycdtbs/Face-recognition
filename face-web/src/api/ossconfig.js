import request from '@/utils/request'

export function getOssInfo() {
    return request({
        url: 'admin/getOssInfo',
        method: 'get'
    })
}
export function saveOssInfo(ossset){
    return request({
        url: 'admin/saveOssInfo',
        method: 'post',
        data:ossset
    })
}