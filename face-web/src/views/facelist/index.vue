<template>
  <div class="app-container">
    <el-table :data="tableData" style="width: 100%" v-loading="listLoading" >
      <el-table-column label="序号" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名" width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ scope.row.userName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.userName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="60">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.userSex }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="open(scope.$index, scope.row)"
            >查看人脸</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getfacelist,getface,delface } from "@/api/facelist";
export default {
  data() {
    return {
      tableData: [],
    };
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true;
      getfacelist().then((response) => {
       let date = response.data.data
       this.tableData = date
       this.listLoading = false;

      });
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
      let username = row.userName + ".jpeg"
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            
          delface(username).then((response) => {
            console.log(response)
            this.tableData.splice(index,1)
          })
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });


    },
     open(index, row) {
       var imageUrl = ''
        console.log(index, row);
        
        let username = row.userName + ".jpeg"
  
        getface(username).then((response) => {
        console.log(response)
        imageUrl = response.data.image
        this.$alert('<img src="'+imageUrl+'"  alt="'+username+'"  height="350" width="250" />', {
          dangerouslyUseHTMLString: true
        });
      });
        
      }
  },
};
</script>
