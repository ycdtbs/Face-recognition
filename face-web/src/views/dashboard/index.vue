<template>
  <div class="app-container">
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
      v-loading="loading"
      element-loading-text="正在加载引擎"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      style="width: 100%"
    >
      <el-form-item label="引擎状态">
        <el-switch
          v-model="form.delivery"
          @change="openEneiges()"
          v-bind:disabled="disable"
        ></el-switch>
      </el-form-item>
      <el-form-item label="识别库利用率">
        <el-progress type="circle" v-bind:percentage="form.redisPer"></el-progress>
      </el-form-item>
      <el-form-item label="识别库人脸数量">
        <el-badge v-bind:value="form.redisDateList.length" class="item">
          <el-button size="small" @click="showList('redis')" value="redis"
            >点击查看</el-button
          >
        </el-badge>
        &nbsp; &nbsp; &nbsp; &nbsp;

        <el-button size="small" @click="loadfaces" value="redis">加载人脸库</el-button>
      </el-form-item>
      <el-form-item label="本地库利用率">
        <el-progress type="circle" v-bind:percentage="form.mysqlPer"></el-progress>
      </el-form-item>
      <el-form-item label="本地库人脸数量">
        <el-badge v-bind:value="form.mysqlDateList.length" class="item">
          <el-button size="small" @click="showList('mysql')" value="redis"
            >点击查看</el-button
          >
        </el-badge>
      </el-form-item>
      
    </el-form>
  </div>
</template>
<script>
import { getSystemState, openEneige, loadface } from "@/api/dashboard";

export default {
  data() {
    return {
      form: {
        delivery: true,
        mysqlDateList: [],
        redisDateList: [],
        mysqlPer: 0,
        redisPer: 0,
      },
      loading: true,
      disable: true,
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      getSystemState().then((response) => {
        let data = response.data;
        console.log(data);
        let eneigerState = data.eneigerState;
        if (eneigerState == 0) {
          this.form.delivery = false;
          this.disable = false;
        } else {
          this.form.delivery = true;
        }
        this.form.mysqlDateList = data.mysqlDate;
        this.form.redisDateList = data.redisDate;
        this.form.mysqlPer = (data.mysqlDate.length / 100) * 100;
        this.form.redisPer = (data.redisDate.length / 100) * 100;
      });
      this.loading = false;
    },
    showList(item) {
      if (item == "redis") {
        this.$notify({
          title: "缓存人脸列表",
          message: this.form.redisDateList,
        });
      }
      if (item == "mysql") {
        this.$notify({
          title: "本地库人脸列表",
          message: this.form.mysqlDateList,
        });
      }
    },
    openEneiges() {
      if (this.form.delivery == false) {
        this.$message("引擎加载完毕，如需重新加载重启系统");
      }
      if (this.form.delivery == true) {
        this.loading = true;
        openEneige().then((response) => {
          this.$alert("引擎加载完成后只在重启后关闭", {
            confirmButtonText: "确定",
            callback: (action) => {
              this.$router.go(0);
            },
          });
        });
      }
    },
    loadfaces() {
      this.loading = true;
      loadface().then((response) => {
        this.loading = false;
        this.$alert("人脸缓存更新成功", {
          confirmButtonText: "确定",
          callback: (action) => {
            this.$router.go(0);
          },
        });
      });
    },
  },
};
</script>
