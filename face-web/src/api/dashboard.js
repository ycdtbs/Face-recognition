import request from '@/utils/request'

export function getSystemState () {
    return request({
        url: '/admin/getSystemState',
        method: 'post'
        })
}
export function openEneige() {
    return request({
        url: '/file/registered_engine',
        method: 'post'
    })
}
export function loadface() {
    return request({
        url: '/file/loadface',
        method: 'post'
    })
}