export function getBase64(file) {
    return new Promise((resolve, reject) => {
        let reader = new FileReader();
        let fileResult = "";
        reader.readAsDataURL(file);
        //开始转
        reader.onload = function () {
            fileResult = reader.result;
        };
        //转 失败
        reader.onerror = function (error) {
            reject(error);
        };
        //转 结束  咱就 resolve 出去
        reader.onloadend = function () {
            resolve(fileResult);
        };
    });
}
