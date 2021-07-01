import request from '@/utils/request'

export function faceDesign(faceObj) {
    return request({
        url: 'admin/faceDesign',
        method: 'post',
        data : faceObj
    })
}