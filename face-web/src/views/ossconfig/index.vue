<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="endpoint">
        <el-input
          v-model="form.endpoint"
          placeholder="yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
String endpoint = yourEndpoint"
        />
      </el-form-item>
      <el-form-item label="accessKeyId">
        <el-input v-model="form.accessKeyId" placeholder="阿里云oss官网的accessKeyId " />
      </el-form-item>
      <el-form-item label="accessKeySecret">
        <el-input
          v-model="form.accessKeySecret"
          placeholder="阿里云oss官网的accessKeySecret "
        />
      </el-form-item>
      <el-form-item label="examplebucket">
        <el-input v-model="form.bucket" placeholder="Bucket " />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">更新</el-button>
        <el-button @click="onCancel">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getOssInfo, saveOssInfo } from "@/api/ossconfig";

export default {
  data() {
    return {
      form: {
        endpoint: "",
        accessKeyId: "",
        accessKeySecret: "",
        bucket: "",
      },
    };
  },
  created() {
    this.initDate();
  },
  methods: {
    initDate() {
      getOssInfo().then((response) => {
        let ossInfoList = response.data.data;
        console.log(ossInfoList);
        for (let index = 0; index < ossInfoList.length; index++) {
          console.log("cuowu");
          let item = ossInfoList[index];
          let name = item.configName;
          let value = item.configValue;
          this.form[name] = value;
          console.log(value);
        }
      });
    },

    onSubmit() {
      console.log(this.form);
      console.log(this.form.endpoint);
      if (this.form.endpoint == "") {
        this.$message({
          message: "endpoint!为必填",
          type: "error",
        });
        return false;
      }
      if (this.form.accessKeyId == "") {
        this.$message({
          message: "accessKeyId!为必填",
          type: "error",
        });
        return false;
      }
      if (this.form.accessKeySecret == "") {
        this.$message({
          message: "accessKeySecret!为必填",
          type: "error",
        });
        return false;
      }
      let ossset = {
        endpoint: this.form.endpoint,
        accessKeyId: this.form.accessKeyId,
        accessKeySecret: this.form.accessKeySecret,
        bucket: this.form.bucket,
      };
      console.log(ossset);
      saveOssInfo(ossset).then((response) => {
        let date = response;
        this.$message({
        message: "修改完成!"
      });
        this.$router.go(0);
      });
    },
    onCancel() {
      this.$message({
        message: "我还没写这个功能嘻嘻嘻!",
        type: "warning",
      });
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>
