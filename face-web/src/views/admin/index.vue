<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="管理员ID" width="110">
        <template slot-scope="scope">
          {{ scope.row.adminId }}
        </template>
      </el-table-column>
      <!--
      <el-table-column label="管理员权限" width="110" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.adminRole == 1" >读/写</span>
          <span v-if="scope.row.adminRole == 2" >只读</span>

        </template>
      </el-table-column>
      -->
      <el-table-column label="创建日期" align="center">
        <template slot-scope="scope">
          {{ scope.row.adminCreatetime | formatDate}}
        </template>
      </el-table-column>
      <!--
      <el-table-column label="操作"  align="center">
        <template slot-scope="scope">
  <el-button type="danger" icon="el-icon-delete" circle></el-button>
        <span v-if="scope.row.adminRole == 2" >    <el-button type="primary" @click="changeRole(scope.$index)"   icon="el-icon-edit" circle></el-button>
</span>
        </template>
      </el-table-column>
      -->
    </el-table>
  </div>
</template>

<script>
import { getAdminList,changeRole } from "@/api/admin";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger"
      };
      return statusMap[status];
    },

    //时间戳转换
    formatDate: function(value) {
      let date = new Date(value);
      let y = date.getFullYear();
      let MM = date.getMonth() + 1;
      MM = MM < 10 ? "0" + MM : MM;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
      let h = date.getHours();
      h = h < 10 ? "0" + h : h;
      let m = date.getMinutes();
      m = m < 10 ? "0" + m : m;
      let s = date.getSeconds();
      s = s < 10 ? "0" + s : s;
      return y + "年" + MM + "月" + d + "日" + " " + h + ":" + m + ":" + s;
    },
    //2020-12-11T05:27:56.370+0000转换
    renderTime: function(date) {
      var dateee = new Date(date).toJSON();
      return new Date(+new Date(dateee) + 8 * 3600 * 1000)
        .toISOString()
        .replace(/T/g, " ")
        .replace(/\.[\d]{3}Z/, "");
    }
  },
  data() {
    return {
      list: null,
      listLoading: true
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.listLoading = true;
      getAdminList().then(response => {
        this.list = response.data.userList;
        this.listLoading = false;
      });
    },
    changeRole(index){
      let userId = this.list[index].adminId
      changeRole(userId).then(response => {
        console.log(response)
      })
    }
  }
};
</script>
