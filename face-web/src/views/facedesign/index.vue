<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px" v-loading="loading">
      <el-form-item label="用户名">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.sex" placeholder="选择你的性别">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="上传头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/file/getimageBase64Code"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加用户</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getBase64 } from "@/utils/image";
import { getfacelist,faceDesign } from "@/api/facedesign";
export default {
  data() {
    return {
      form: {
        name: "",
        sex: "",
      },
      imageUrl: "",
      imageBase64: "",
      loading: false,
    };
  },
  methods: {
    onSubmit() {
      if (this.form.name == "") {
        this.$message({
          message: "用户名不能为空",
          type: "warning",
        });
        return false;
      }
      if (this.form.sex == "") {
        this.$message({
          message: "性别不能为空",
          type: "warning",
        });
        return false;
      }
      if (this.imageBase64 == "") {
        this.$message({
          message: "请选择图片",
          type: "warning",
        });
        return false;
      }
      let faceObj = {
        face_user: this.form.name,
        face_sex: this.form.sex,
        face_base64: this.imageBase64,
      };
      this.loading = true;
      faceDesign(faceObj).then((rep) => {
        this.loading = false;
        console.log(this.imageBase64)
        this.$message({
          message: "成功!",
        });

         this.$alert("上传成功", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$router.go(0);
            },
          });
      });
    },
    onCancel() {
      this.$message({
        message: "cancel!",
        type: "warning",
      });
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);

      getBase64(file.raw).then((resp) => {
        this.imageBase64 = resp;
      });
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
